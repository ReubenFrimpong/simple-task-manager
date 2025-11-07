import { Component, inject } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth-service';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-registration',
  imports: [MatCardModule, MatButtonModule, MatFormFieldModule, MatInputModule, MatIconModule, FormsModule, RouterLink],
  templateUrl: './registration.html',
  styleUrl: './registration.scss'
})
export class Registration {
  authService = inject(AuthService);
  userDetails = {
    username: '',
    password: '',
  };

  public register() {
    this.authService.register(this.userDetails);
  }
}
