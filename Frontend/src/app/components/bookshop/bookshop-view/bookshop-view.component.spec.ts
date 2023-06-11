import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookshopViewComponent } from './bookshop-view.component';

describe('BookshopViewComponent', () => {
  let component: BookshopViewComponent;
  let fixture: ComponentFixture<BookshopViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookshopViewComponent]
    });
    fixture = TestBed.createComponent(BookshopViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
