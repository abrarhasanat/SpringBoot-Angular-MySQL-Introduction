import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookAddComponent } from './components/book/book-add/book-add.component';
import { BookEditComponent } from './components/book/book-edit/book-edit.component';
import { BookListComponent } from './components/book/book-list/book-list.component';
import { BookComponent } from './components/book/book.component';
import { BookshopListComponent } from './components/bookshop/bookshop-list/bookshop-list.component';
import { BookshopViewComponent } from './components/bookshop/bookshop-view/bookshop-view.component';
import { BookshopComponent } from './components/bookshop/bookshop.component';
import { BookService } from './services/bookservices/book.service';
import { BookshopAddComponent } from './components/bookshop/bookshop-add/bookshop-add.component';
import { BookshopEditComponent } from './components/bookshop/bookshop-edit/bookshop-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    BookListComponent,
    BookshopListComponent,
    BookshopComponent,
    BookAddComponent,
    BookshopViewComponent,
    BookEditComponent,
    BookshopAddComponent,
    BookshopEditComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatCardModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatIconModule,
    MatListModule,
    MatToolbarModule,
  ],
  providers: [BookService],
  bootstrap: [AppComponent],
})
export class AppModule {}
