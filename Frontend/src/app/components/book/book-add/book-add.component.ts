import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BookService } from '../../../services/bookservices/book.service';

@Component({
  selector: 'app-book-add',
  templateUrl: './book-add.component.html',
  styleUrls: ['./book-add.component.css'],
})
export class BookAddComponent implements OnInit {
  bookForm: FormGroup;
  // bookshops: Bookshop[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private bookService: BookService,
    // private bookShopService: BookshopService,
    private router: Router
  ) {
    this.bookForm = this.formBuilder.group({
      title: ['', Validators.required],
      author: ['', Validators.required],
      country: ['', Validators.required],
      language: ['', Validators.required],
      link: ['', Validators.required],
      pages: ['', [Validators.required, Validators.min(0)]],
      year: ['', [Validators.required, Validators.min(0)]],
      price: ['', [Validators.required, Validators.min(0)]],
      // bookshop: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    // this.fetchBookshops();
  }

  // fetchBookshops(): void {
  //   this.bookShopService.getBookshops().subscribe(
  //     (response: Bookshop[]) => {
  //       this.bookshops = response;
  //       console.log('Bookshops loaded successfully');
  //     },
  //     (error) => {
  //       console.error('Error loading bookshops:', error);
  //     }
  //   );
  // }

  onSubmit(): void {
    if (this.bookForm.valid) {
      this.bookService.addBook(this.bookForm.value).subscribe(
        () => {
          console.log('Book added successfully');
          this.router.navigate(['/book']);
        },
        (error) => {
          console.error('Error adding book:', error);
        }
      );
    }
  }
}
