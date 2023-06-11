import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookshopListComponent } from './bookshop-list.component';

describe('BookshopListComponent', () => {
  let component: BookshopListComponent;
  let fixture: ComponentFixture<BookshopListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookshopListComponent],
    });
    fixture = TestBed.createComponent(BookshopListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
