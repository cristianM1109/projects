import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';


@Component({
  selector: 'app-structure',
  templateUrl: './structure.component.html',
  styleUrl: './structure.component.css',
})
export class StructureComponent {
  pageTitle: string = '';

  constructor(private activaredRoute: ActivatedRoute, private router: Router, private titleService:Title) {
    this.pageTitle = this.getChild(this.activaredRoute).data.value.title;
  }

  ngOnInit() {
    //console.log(this.activaredRoute);
    console.log(this.router);
    this.router.events.pipe(filter((event) => event instanceof NavigationEnd)).subscribe((changeEvent)=>{
      console.log(changeEvent);
      this.getChild(this.activaredRoute).data.subscribe((value: any) => {
        //console.log(value);
        this.pageTitle = value['title'];
        //console.log(this.pageTitle);
        this.titleService.setTitle('DashApp' + ' - ' + this.pageTitle);
      });
    });
   
  }

  getChild(activatedRoute: ActivatedRoute): any {
    if (activatedRoute.firstChild) {
      console.log('if', activatedRoute);
      return this.getChild(activatedRoute.firstChild);
    } else {
      console.log('else', activatedRoute);
      return activatedRoute;
    }
  }
}
