import { Component } from '@angular/core';
import { HttpClientService } from '../services/http-client.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.sass'
})
export class LoginComponent {
  url: string = "";

  constructor(private http:HttpClientService){}

  ngOnInit(): void {
    this.http.get("/api/v1/auth/url").subscribe((data: any) => {
      this.url = data.url;
    })
  }
}

