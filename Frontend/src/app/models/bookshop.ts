import { Book } from './book';

export interface Bookshop {
  id: number;
  shopNumber: string;
  shopName: string;
  location: string;
  books: Book[];
  contactNumber: string;
  email: string;
}
