import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Child1Component } from './child1/child1.component';
import { Child2Component } from './child2/child2.component';
import { Grandchild3Component } from './grandchild3/grandchild3.component';
import { Grandchild4Component } from './grandchild4/grandchild4.component';

@NgModule({
  declarations: [
    AppComponent,
    Child1Component,
    Child2Component,
    Grandchild3Component,
    Grandchild4Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
