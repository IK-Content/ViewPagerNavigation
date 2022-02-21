package com.jasontsh.interviewkickstart.viewpagernavigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.common.collect.ImmutableList

class ViewPagerViewModel : ViewModel() {
    val stepList: MutableLiveData<ImmutableList<Int>> by lazy {
        MutableLiveData<ImmutableList<Int>>(ImmutableList.of(0, 0, 0, 0))
    }

    val currentPosition: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }
}