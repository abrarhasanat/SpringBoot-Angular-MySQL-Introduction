import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../../models/book';
import { BookshopService } from '../bookshopservices/bookshop.service';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  private bookApiUrl;

  constructor(
    private http: HttpClient,
    private bookshopService: BookshopService
  ) {
    this.bookApiUrl = 'http://localhost:8080/api/books';
  }

  public getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.bookApiUrl);
  }

  public deleteBook(id: number): Observable<void> {
    const url = `${this.bookApiUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  public addBook(book: Book): Observable<Book[]> {
    // const { bookshop, ...bookDetails } = bookData;
    const bookArr: Book[] = [book];
    return this.http.post<any>(this.bookApiUrl, bookArr);
    // .pipe(
    //   switchMap((response) => {
    //     const bookId = response[0].id;
    //     return this.bookshopService.addBookToBookshop(bookshop, bookId);
    //   })
    // );
  }

  public getTheBook(id: number): Observable<Book> {
    const url = `${this.bookApiUrl}/${id}`;
    return this.http.get<Book>(url);
  }

  public editBook(updatedBook: Book): Observable<Book> {
    const url = `${this.bookApiUrl}/${updatedBook.id}`;
    return this.http.put<any>(url, updatedBook);
  }
}
