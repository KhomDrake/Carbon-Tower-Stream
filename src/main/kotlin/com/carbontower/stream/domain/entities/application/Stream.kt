package com.carbontower.stream.domain.entities.application

data class Stream(val idStream: Int, val language: String, val title: String, val idUserStream_fk: Int, val viewCount: Int)