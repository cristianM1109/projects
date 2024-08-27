import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { BooksService } from "../../services/books.service";

@Component({
  selector: "app-book-details",
  templateUrl: "./book-details.component.html",
  styleUrls: ["./book-details.component.css"],
})
export class BookDetailsComponent implements OnInit {

  book:any;
  bookId:any;
  routeParamsObservable: any;
  editMode:boolean = false;

  constructor(private activatedRoute : ActivatedRoute,private bookService: BooksService) {}

  ngOnInit(): void {
    // this.bookId = this.activatedRoute.snapshot.paramMap.get('id');
    // this.book = this.bookService.books.find((book) => book.id == this.bookId);

    this.routeParamsObservable = this.activatedRoute.paramMap.subscribe((params) =>{
      this.bookId = params.get('id');
      this.book = this.bookService.books.find((book) => book.id == this.bookId);
    })

    this.activatedRoute.queryParams.subscribe((params) => {
      this.editMode = Boolean(params['edit']);
     });
     

  }

  ngOnDestroy():void {
    this.routeParamsObservable.unsubscribe();
  }

}
