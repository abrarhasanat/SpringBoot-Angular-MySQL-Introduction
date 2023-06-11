import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bookshop } from '../../../models/bookshop';
import { BookshopService } from '../../../services/bookshopservices/bookshop.service';

@Component({
  selector: 'app-bookshoplist',
  templateUrl: './bookshop-list.component.html',
  styleUrls: ['./bookshop-list.component.css'],
})
export class BookshopListComponent implements OnInit {
  bookshops: Bookshop[] = [];
  bookshopToView!: Bookshop;
  constructor(
    private bookshopService: BookshopService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchBookshops();
  }

  fetchBookshops(): void {
    this.bookshopService.getBookshops().subscribe(
      (response: Bookshop[]) => {
        this.bookshops = response;
        console.log(this.bookshops);
      },
      (error) => {
        console.error('Error fetching bookshops:', error);
      }
    );
  }

  deleteBookshop(id: number): void {
    this.bookshopService.deleteBookshop(id).subscribe(
      () => {
        console.log('Bookshop deleted successfully');
        // Refresh the bookshop list after deletion
        this.fetchBookshops();
      },
      (error) => {
        console.error('Error deleting bookshop:', error);
      }
    );
  }

  viewBookshop(id: number): void {
    this.bookshopService.viewBookshop(id).subscribe(
      (response: Bookshop) => {
        this.router.navigate(['/view-bookshop'], {
          state: { toViewbookshop: response },
        });
        console.log('Bookshop viewed successfully');
      },
      (error) => {
        console.error('Error viewing bookshop:', error);
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
}
