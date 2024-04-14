import { Component, signal } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'instagrammmm';

 s=signal(1);
 constructor(){
  
 }
}
