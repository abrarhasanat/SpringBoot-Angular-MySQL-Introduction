import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Book } from '../../../models/book';

import { Router } from '@angular/router';
import { BookService } from '../../../services/bookservices/book.service';
@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css'],
})
export class BookEditComponent {
  @Input() book!: Book;

  editBookForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private bookService: BookService,
    private router: Router
  ) {
    this.editBookForm = this.formBuilder.group({
      title: ['', Validators.required],
      author: ['', Validators.required],
      country: ['', Validators.required],
      language: ['', Validators.required],
      link: ['', Validators.required],
      pages: ['', [Validators.required, Validators.min(0)]],
      year: ['', [Validators.required, Validators.min(0)]],
      price: ['', [Validators.required, Validators.min(0)]],
    });
  }
  ngOnInit(): void {
    this.book = history.state.toUpdatebook;
    if (this.book) {
      this.editBookForm.patchValue({
        title: this.book.title,
        author: this.book.author,
        country: this.book.country,
        language: this.book.language,
        link: this.book.link,
        pages: this.book.pages,
        year: this.book.year,
        price: this.book.price,
      });
    }
  }

  onSubmit(): void {
    if (this.editBookForm.valid) {
      const updatedBook: Book = {
        id: this.book.id,
        title: this.editBookForm.value.title,
        author: this.editBookForm.value.author,
        country: this.editBookForm.value.country,
        language: this.editBookForm.value.language,
        link: this.editBookForm.value.link,
        pages: this.editBookForm.value.pages,
        year: this.editBookForm.value.year,
        price: this.editBookForm.value.price,
      };

      this.bookService.editBook(updatedBook).subscribe(
        () => {
          console.log('Book edited successfully');
          this.router.navigate(['/book']);
        },
        (error) => {
          console.error('Error adding book:', error);
        }
      );
    }
  }
}
