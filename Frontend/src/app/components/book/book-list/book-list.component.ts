import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../../../models/book';
import { BookService } from '../../../services/bookservices/book.service';

@Component({
  selector: 'app-booklist',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css'],
})
export class BookListComponent implements OnInit {
  books: Book[] = [];

  constructor(private bookService: BookService, private router: Router) {}

  ngOnInit(): void {
    this.fetchBooks();
  }

  fetchBooks(): void {
    this.bookService.getBooks().subscribe(
      (response: Book[]) => {
        this.books = response;
        console.log(this.books);
      },
      (error) => {
        console.error('Error fetching books:', error);
      }
    );
  }

  deleteBook(id: number): void {
    this.bookService.deleteBook(id).subscribe(
      () => {
        console.log('Book deleted successfully');
        // Refresh the book list after deletion
        this.fetchBooks();
      },
      (error) => {
        console.error('Error deleting book:', error);
      }
    );
  }

  editBookCall(id: number): void {
    this.bookService.getTheBook(id).subscribe(
      (response: Book) => {
        console.log(response);
        this.router.navigate(['/edit-book'], {
          state: { toUpdatebook: response },
        });
      },
      (error) => {
        console.error('Error getting the Book:', error);
      }
    );
  }
}
