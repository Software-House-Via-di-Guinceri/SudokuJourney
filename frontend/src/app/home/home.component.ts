import { Component } from '@angular/core';
import { HttpClientService } from '../services/http-client.service';
import { time } from 'console';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.sass'
})
export class HomeComponent {

  content: string = "";

  constructor(private http:HttpClientService){}

  ngOnInit(): void {
    this.content = new Date().toString();
  }
}
