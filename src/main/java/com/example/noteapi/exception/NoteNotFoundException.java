package com.example.noteapi.exception;

public class NoteNotFoundException extends RuntimeException {

  public NoteNotFoundException(Integer id) {
    super("指定されたIDのノートが見つかりません: " + id);
  }
}
