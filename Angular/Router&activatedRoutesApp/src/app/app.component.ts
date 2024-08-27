import { Component ,OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'AngularRouting';

  constructor(private activatedRoute:ActivatedRoute){}

  ngOnInit():void{
    this.activatedRoute.fragment.subscribe((value) =>{
      this.jumpTo(value);
    });
  }

  jumpTo(section:any):void{
      document.getElementById(section)?.scrollIntoView({behavior:'smooth'})
  };

}