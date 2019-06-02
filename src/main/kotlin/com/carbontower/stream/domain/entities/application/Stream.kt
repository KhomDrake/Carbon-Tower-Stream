package com.carbontower.stream.domain.entities.application

data class Stream(val idStream: String, val language: String, val title: String, val idUserStream_fk: String, val viewCount: Int)