import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookshopAddComponent } from './bookshop-add.component';

describe('BookshopAddComponent', () => {
  let component: BookshopAddComponent;
  let fixture: ComponentFixture<BookshopAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookshopAddComponent]
    });
    fixture = TestBed.createComponent(BookshopAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
