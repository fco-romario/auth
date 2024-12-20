import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { Observable, Subscriber, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private readonly url = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) { }

  login(email: string, password: string) {
    return this.http.post<LoginResponse>(`${this.url}/login`, { email, password }).pipe(
      tap((value) => {
        sessionStorage.setItem('auth-token', value.token);
        sessionStorage.setItem('auth-name', value.name);
      })
    );
  }

  signup(name: string, email: string, password: string) {
    return this.http.post<LoginResponse>(`${this.url}/register`, { name, email, password }).pipe(
      tap((values) => {
        sessionStorage.setItem('auth-token', values.token);
        sessionStorage.setItem('auth-name', values.name);
      })
    )
  }
}
