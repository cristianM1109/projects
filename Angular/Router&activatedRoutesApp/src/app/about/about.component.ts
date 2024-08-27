import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  constructor(private router : Router, private activatedRoute: ActivatedRoute ) { }

  ngOnInit(): void {
  }

  navigateToHome(){
      this.router.navigate(['../home'],{relativeTo:this.activatedRoute})
  }

}