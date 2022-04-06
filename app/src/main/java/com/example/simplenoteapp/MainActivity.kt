package com.example.simplenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplenoteapp.data.NotesDataSource
import com.example.simplenoteapp.model.Note
import com.example.simplenoteapp.screen.NoteScreen
import com.example.simplenoteapp.screen.NoteViewModel
import com.example.simplenoteapp.ui.theme.SimpleNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //Identificerer at det her er dependency containeren, så dependenciesene kan fås her
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleNoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                        val noteViewModel = viewModel<NoteViewModel>()
                        //val noteViewModel: NoteViewModel by viewModels()
                        NotesApp(noteViewModel)
                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel) {
    val notesList = noteViewModel.noteList.collectAsState().value

    NoteScreen(notes = notesList,
        onAddNote = {noteViewModel.addNote(it)},
        onRemoveNote = {noteViewModel.removeNote(it)})
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleNoteAppTheme {

    }
}