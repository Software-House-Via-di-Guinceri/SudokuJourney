import { Component } from '@angular/core';
import { HttpClientService } from '../services/http-client.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.sass'
})
export class ProfileComponent {

  content: string = "";

  constructor(private http:HttpClientService){}

  ngOnInit(): void {
    this.http.get("/api/users").subscribe((data: any) => {
      this.content = data;
    })
  }
}