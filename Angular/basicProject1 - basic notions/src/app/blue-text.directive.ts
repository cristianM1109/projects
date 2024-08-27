import { Directive, Renderer2,ElementRef } from '@angular/core';

@Directive({
  selector: '[BlueTextDirective]'
})
export class BlueTextDirective {

  constructor(private el:ElementRef, private renderer:Renderer2) { 
    this.renderer.setStyle(this.el.nativeElement,'color','blue'); 
  }

}
