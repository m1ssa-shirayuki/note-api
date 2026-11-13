package com.example.noteapi.service;

import com.example.noteapi.dto.Note;
import com.example.noteapi.exception.NoteArgumentNotValidException;
import com.example.noteapi.exception.NoteNotFoundException;
import com.example.noteapi.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // 全件取得
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    // 新規登録
    public Note saveNote(Note note) {
        if (note.getTitle() == null || note.getTitle().isEmpty()) {
            throw new NoteArgumentNotValidException("title is empty");
        }

        if (note.getContent() == null || note.getContent().isEmpty()) {
            throw new NoteArgumentNotValidException("content is empty");
        }

        noteRepository.insertNote(note);
        return note;
    }

    // ID検索
    public Note findById(long id) {
        Note note = noteRepository.findById(id);

        if (note == null) {
            throw new NoteNotFoundException(id);
        }

        return note;
    }

    // 削除
    public boolean delete(long id) {
        if (!noteRepository.delete(id)) {
            throw new NoteNotFoundException(id);
        }

        return true;
    }

    // 更新
    public Note update(Note note, long id) {
        if (note.getTitle() == null || note.getTitle().isEmpty()) {
            throw new NoteArgumentNotValidException("title is empty");
        }

        if (note.getContent() == null || note.getContent().isEmpty()) {
            throw new NoteArgumentNotValidException("content is empty");
        }

        Note targetNote = noteRepository.findById(id);

        if (targetNote == null) {
            throw new NoteNotFoundException(id);
        }

        note.setId(id);
        note.setCreatedAt(targetNote.getCreatedAt());

        return noteRepository.update(note);
    }
}