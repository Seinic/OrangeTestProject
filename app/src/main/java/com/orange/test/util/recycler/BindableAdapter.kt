package com.orange.test.util.recycler

interface BindableAdapter<T> {
    fun setData(data: T)
    fun changedPositions(positions: Set<Int>)
}