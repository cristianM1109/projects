import { Component, OnInit } from '@angular/core';
import { BooksService } from '../services/books.service';

@Component({
  selector: 'app-books-overview',
  templateUrl: './books-overview.component.html',
  styleUrls: ['./books-overview.component.css']
})
export class BooksOverviewComponent implements OnInit {

  constructor(private booksService: BooksService) { }

  books: any = [];

  ngOnInit(): void {
    this.books = this.booksService.books;
  }

}