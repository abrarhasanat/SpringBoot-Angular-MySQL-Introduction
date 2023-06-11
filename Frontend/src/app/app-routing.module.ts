import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookAddComponent } from './components/book/book-add/book-add.component';
import { BookEditComponent } from './components/book/book-edit/book-edit.component';
import { BookComponent } from './components/book/book.component';
import { BookshopAddComponent } from './components/bookshop/bookshop-add/bookshop-add.component';
import { BookshopEditComponent } from './components/bookshop/bookshop-edit/bookshop-edit.component';
import { BookshopViewComponent } from './components/bookshop/bookshop-view/bookshop-view.component';
import { BookshopComponent } from './components/bookshop/bookshop.component';

// routes are here
const routes: Routes = [
  { path: '', redirectTo: '/bookshop', pathMatch: 'full' },
  { path: 'book', component: BookComponent },
  { path: 'bookshop', component: BookshopComponent },
  { path: 'add-book', component: BookAddComponent },
  { path: 'add-bookshop', component: BookshopAddComponent },
  { path: 'edit-book', component: BookEditComponent },
  { path: 'edit-bookshop', component: BookshopEditComponent },
  { path: 'view-bookshop', component: BookshopViewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
