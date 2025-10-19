package com.example.memoto.kafka;

import com.example.memoto.model.Memo;

public record MemoEvent(String eventType, Memo memo) { }
