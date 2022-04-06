package com.example.simplenoteapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplenoteapp.data.NotesDataSource
import com.example.simplenoteapp.model.Note
import com.example.simplenoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    //private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    /*Når den initialiseres fyldes den med dummy data*/
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect {
                listOfNotes ->
                if (listOfNotes.isNullOrEmpty()) {
                    Log.d("Empty","Empty list")
                }
                else {
                    _noteList.value = listOfNotes
                }
            }
        }
        //noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }

    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }

    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
}