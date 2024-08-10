import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { HttpClientService } from './services/http-client.service';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, ProfileComponent,HomeComponent,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {
  title = 'frontend';

  componentToShow: string = "public";

  constructor(private http:HttpClientService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      if (params.get("code") !== undefined){
        this.http.getToken(params.get("code")!).subscribe((result: boolean) => {
          if (result == true){
            this.componentToShow = "private";
          } else {
            this.componentToShow = "public";
          }
        })
      }
    })
  }
}
