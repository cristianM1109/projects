import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'

import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { BooksOverviewComponent } from './books/books-overview.component';
import { BookDetailsComponent } from './books/book/book-details.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  {  path:'' , redirectTo:'home', pathMatch:'full'},
  {  path:'home' , component:HomeComponent },
  {  path:'about' , component:AboutComponent },
  {  path:'contact' , component:ContactComponent },
  {  path:'books' , component:BooksOverviewComponent },
  {  path:'books/book/:id' , component:BookDetailsComponent },
  {  path:'**', component: PageNotFoundComponent },
  ];

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    ContactComponent,
    BooksOverviewComponent,
    BookDetailsComponent,
    HomeComponent,
    PageNotFoundComponent,
  ],
  imports: [BrowserModule,FormsModule, RouterModule.forRoot(appRoutes)],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
