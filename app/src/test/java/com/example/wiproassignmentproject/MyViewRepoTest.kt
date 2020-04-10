package com.example.wiproassignmentproject

import org.junit.Rule
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MyViewRepoTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

}