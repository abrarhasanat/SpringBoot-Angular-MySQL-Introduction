import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/bookservices/book.service';
import { BookshopService } from '../../../services/bookshopservices/bookshop.service';

@Component({
  selector: 'app-bookshop-add',
  templateUrl: './bookshop-add.component.html',
  styleUrls: ['./bookshop-add.component.css'],
})
export class BookshopAddComponent implements OnInit {
  bookshopForm: FormGroup;
  books: Book[] = [];
  selectedBooks: Book[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private bookshopService: BookshopService,
    private bookService: BookService,
    private router: Router
  ) {
    this.bookshopForm = this.formBuilder.group({
      shopNumber: [Validators.required, Validators.min(0)],
      shopName: ['', Validators.required],
      location: ['', Validators.required],
      contactNumber: [Validators.required, Validators.min(0)],
      email: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.fetchBooks();
  }

  fetchBooks(): void {
    this.bookService.getBooks().subscribe(
      (response: Book[]) => {
        this.books = response;
        console.log('Books loaded successfully');
      },
      (error) => {
        console.error('Error loading books:', error);
      }
    );
  }

  toggleSelection(book: Book): void {
    const index = this.selectedBooks.findIndex(
      (selectedBook) => selectedBook.id === book.id
    );
    if (index > -1) {
      this.selectedBooks.splice(index, 1);
    } else {
      this.selectedBooks.push(book);
    }
  }

  onSubmit(): void {
    if (this.bookshopForm.valid) {
      const bookshopData = {
        id: 0,
        shopName: this.bookshopForm.value.shopName,
        shopNumber: this.bookshopForm.value.shopNumber,
        location: this.bookshopForm.value.location,
        contactNumber: this.bookshopForm.value.contactNumber,
        email: this.bookshopForm.value.email,
        books: this.selectedBooks,
      };
      console.log(bookshopData);
      this.bookshopService.addBookshop(bookshopData).subscribe(
        () => {
          console.log('Bookshop added successfully');
          this.router.navigate(['/bookshop']);
        },
        (error) => {
          console.error('Error adding bookshop:', error);
        }
      );
    }
  }
}
