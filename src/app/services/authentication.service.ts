import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {User} from "../models/user";
import {environment} from "../../environments/environment";
import {UserService} from "./user.service";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
	private currentUserSubject: BehaviorSubject<User>;
	public currentUser: Observable<User>;

	constructor(private http: HttpClient,
				private userService: UserService) {
		this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
		this.currentUser = this.currentUserSubject.asObservable();
	}

	public get currentUserValue(): User {
		return this.currentUserSubject.value;
	}

	public get currentUserLocation(): string {
		return `/${this.userService.usersEndpoint}/${this.currentUserValue.id}`;
	}

	login(username, password) {
		return this.http
				.post<any>(
						`${environment.apiUrl}/users/authenticate`,
						{ username: username, password })
				.pipe(map(user => {
					// store user details and jwt token in local storage to keep user logged in between page refreshes
					localStorage.setItem('currentUser', JSON.stringify(user));
					this.currentUserSubject.next(user);
					return user;
				}));
	}

	logout() {
		// remove user from local storage and set current user to null
		localStorage.removeItem('currentUser');
		this.currentUserSubject.next(null);
	}
}
