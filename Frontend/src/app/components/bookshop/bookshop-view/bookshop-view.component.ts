import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { Bookshop } from '../../../models/bookshop';
import { BookService } from '../../../services/bookservices/book.service';
import { BookshopService } from '../../../services/bookshopservices/bookshop.service';

@Component({
  selector: 'app-bookshop-view',
  templateUrl: './bookshop-view.component.html',
  styleUrls: ['./bookshop-view.component.css'],
})
export class BookshopViewComponent {
  @Input() bookshop!: Bookshop;
  constructor(
    private bookService: BookService,
    private bookshopService: BookshopService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.bookshop = history.state.toViewbookshop;
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

  deleteBookshop(id: number): void {
    this.bookshopService.deleteBookshop(id).subscribe(
      () => {
        console.log('Bookshop deleted successfully');
        this.router.navigate(['/bookshop']);
      },
      (error) => {
        console.error('Error deleting bookshop:', error);
      }
    );
  }

  editBookshopCall(id: number): void {
    this.bookshopService.getTheBookshop(id).subscribe(
      (response: Bookshop) => {
        console.log(response);
        this.router.navigate(['/edit-bookshop'], {
          state: { toUpdatebookshop: response },
        });
      },
      (error) => {
        console.error('Error getting the Bookshop:', error);
      }
    );
  }
  deleteBook(id: number): void {
    this.bookshopService.deleteBookFromBookshop(this.bookshop.id, id).subscribe(
      () => {
        console.log('Book deleted successfully');
        this.bookshopService.getTheBookshop(this.bookshop.id).subscribe(
          (response: Bookshop) => {
            this.bookshop = response;
            console.log('Bookshop updated:', this.bookshop);
          },
          (error) => {
            console.error('Error getting the updated Bookshop:', error);
          }
        );
      },
      (error) => {
        console.error('Error deleting book:', error);
      }
    );
  }
}
