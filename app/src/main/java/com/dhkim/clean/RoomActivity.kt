package com.dhkim.clean

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhkim.clean.base.BaseActivity
import com.dhkim.clean.databinding.ActivityRoomBinding
import com.dhkim.clean.utils.Utils.Companion.repeatOnStarted
import com.dhkim.clean.view.adapters.TextListAdapter
import com.dhkim.clean.viewmodel.RoomViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class RoomActivity : BaseActivity<ActivityRoomBinding>() {

    private val viewModel: RoomViewModel by inject()
    override val layoutResID: Int = R.layout.activity_room
    private var textListAdapter: TextListAdapter? = null

    override fun initVariable() {
        binding.viewModel = viewModel
    }

    override fun initListener() {
        with(binding) {
            etPuttext.doOnTextChanged { text, start, before, count ->
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel?.getSearchTexts(text.toString())?.collect {
                        CoroutineScope(Dispatchers.Main).launch {
                            textListAdapter?.setItem(it)
                        }
                        viewModel?.noDataNotification?.set(it.isEmpty())
                    }
                }
            }
            btnPuttext.setOnClickListener {
                viewModel?.insertText(tlPuttext.editText?.text.toString())
                tlPuttext.editText?.setText("")
                hideKeyboard()
            }
        }
    }

    override fun initObserver() {
        repeatOnStarted {
            viewModel.getAllTexts().collect{
                textListAdapter?.setItem(it)
            }
        }
    }

    override fun initView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        textListAdapter = TextListAdapter()
        binding.apply {
            rvTextList.apply {
                setHasFixedSize(true)
                this.layoutManager = layoutManager
                adapter = textListAdapter
            }
        }
        initSwipe()
    }

    /**
     * Hiding keyboard
     */
    private fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private val p: Paint = Paint()
    private fun initSwipe() {
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT /* | ItemTouchHelper.RIGHT */) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    textListAdapter?.getItem()?.get(position)?.let { viewModel.deleteText(it) }
                } else {
                    // 오른쪽으로 밀었을때
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                var icon: Bitmap
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView: View = viewHolder.itemView
                    val height =
                        itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3
                    if (dX > 0) {
                        // 오른쪽으로 밀었을 때
                    } else {
                        p.color = Color.parseColor("#D32F2F")
                        val background = RectF(
                            itemView.right.toFloat() + dX,
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat()
                        )

                        // c.drawRect(background, p)
                        val radius = resources.getDimension(R.dimen._7dp)

                        c.drawRoundRect(background, radius, radius, p)
                        // icon
                        icon = BitmapFactory.decodeResource(resources, R.drawable.icon_delete)
                        val iconDest = RectF(
                            itemView.right - 2 * width,
                            itemView.top + width,
                            itemView.right - width,
                            itemView.bottom - width
                        )
                        c.drawBitmap(icon, null, iconDest, p)
                    }
                }

            }

        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvTextList)

    }
}