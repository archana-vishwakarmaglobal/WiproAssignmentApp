package com.example.wiproassignmentproject.di

import com.example.wiproassignmentproject.MainActivity
import dagger.Component


@Component
interface MyAppComponent {
    fun inject(mainActivity: MainActivity)
}