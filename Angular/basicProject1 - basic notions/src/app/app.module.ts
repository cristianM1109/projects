import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ContainerComponent } from './container/container.component';
import { NavComponent } from './nav/nav.component';
import { HeaderComponent } from './header/header.component';
import { MessageComponent } from './message/message.component';
import { SearchComponent } from './search/search.component';
import { FormsModule } from '@angular/forms';
import { BlueTextDirective } from './blue-text.directive';
import { ProductsComponent } from './products/products.component';

@NgModule({
  declarations: [
    AppComponent,
    ContainerComponent,
    NavComponent,
    HeaderComponent,
    MessageComponent,
    SearchComponent,
    BlueTextDirective,
    ProductsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
