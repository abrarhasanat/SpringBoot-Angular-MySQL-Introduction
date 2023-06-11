import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookshopComponent } from './bookshop.component';

describe('BookshopComponent', () => {
  let component: BookshopComponent;
  let fixture: ComponentFixture<BookshopComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookshopComponent],
    });
    fixture = TestBed.createComponent(BookshopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
