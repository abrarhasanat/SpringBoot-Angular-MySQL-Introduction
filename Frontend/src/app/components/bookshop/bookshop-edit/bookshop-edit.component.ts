import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Bookshop } from '../../../models/bookshop';

import { Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/bookservices/book.service';
import { BookshopService } from '../../../services/bookshopservices/bookshop.service';
@Component({
  selector: 'app-bookshop-edit',
  templateUrl: './bookshop-edit.component.html',
  styleUrls: ['./bookshop-edit.component.css'],
})
export class BookshopEditComponent {
  @Input() bookshop!: Bookshop;

  editBookshopForm: FormGroup;
  booksInBookshop: Book[] = [];
  booksNotInBookshop: Book[] = [];
  selectedBooksInBookshop: Book[] = [];
  selectedBooksNotInBookshop: Book[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private bookshopService: BookshopService,
    private bookService: BookService,
    private router: Router
  ) {
    this.editBookshopForm = this.formBuilder.group({
      shopNumber: [Validators.required, Validators.min(0)],
      shopName: ['', Validators.required],
      location: ['', Validators.required],
      contactNumber: [Validators.required, Validators.min(0)],
      email: ['', Validators.required],
    });
  }
  ngOnInit(): void {
    this.bookshop = history.state.toUpdatebookshop;
    if (this.bookshop) {
      this.editBookshopForm.patchValue({
        shopNumber: this.bookshop.shopNumber,
        shopName: this.bookshop.shopName,
        location: this.bookshop.location,
        contactNumber: this.bookshop.contactNumber,
        email: this.bookshop.email,
      });
      this.booksInBookshop = this.bookshop.books;
      this.getBooksByShopStatus();
      this.selectedBooksInBookshop = [...this.booksInBookshop];
    }
  }

  toggleSelectionLeft(book: Book): void {
    const index = this.selectedBooksInBookshop.findIndex(
      (selectedBooksInBookshop) => selectedBooksInBookshop.id === book.id
    );
    if (index > -1) {
      this.selectedBooksInBookshop.splice(index, 1);
    } else {
      this.selectedBooksInBookshop.push(book);
    }
  }

  toggleSelectionRight(book: Book): void {
    const index = this.selectedBooksNotInBookshop.findIndex(
      (selectedBooksNotInBookshop) => selectedBooksNotInBookshop.id === book.id
    );
    if (index > -1) {
      this.selectedBooksNotInBookshop.splice(index, 1);
    } else {
      this.selectedBooksNotInBookshop.push(book);
    }
  }

  getBooksByShopStatus(): void {
    this.bookService.getBooks().subscribe((allBooks) => {
      this.booksNotInBookshop = allBooks.filter(
        (book) => !this.booksInBookshop.some((b) => b.id === book.id)
      );
    });
  }
  onSubmit(): void {
    if (this.editBookshopForm.valid) {
      const updatedBookshop: Bookshop = {
        id: this.bookshop.id,
        shopNumber: this.editBookshopForm.value.shopNumber,
        shopName: this.editBookshopForm.value.shopName,
        location: this.editBookshopForm.value.location,
        contactNumber: this.editBookshopForm.value.contactNumber,
        email: this.editBookshopForm.value.email,
        books: [],
      };

      console.log('here2');
      console.log(this.selectedBooksInBookshop);
      console.log(this.selectedBooksNotInBookshop);

      this.bookshopService
        .editBookshop(
          updatedBookshop,
          this.selectedBooksNotInBookshop,
          this.selectedBooksInBookshop
        )
        .subscribe(
          () => {
            console.log('Bookshop edited successfully');
            this.router.navigate(['/bookshop']);
          },
          (error) => {
            console.error('Error editing bookshop:', error);
          }
        );
    }
  }
}
