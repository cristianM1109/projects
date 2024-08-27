import { Component, OnInit } from '@angular/core';
import { BooksService } from '../services/books.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private booksService: BooksService) {}

  books: any = [];

  ngOnInit(): void {
    this.books = this.booksService.books;
  }
}
