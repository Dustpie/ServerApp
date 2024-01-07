import { Component, OnInit } from '@angular/core';
import { ServerService } from './service/server.service';
import { Observable, catchError, map, of, startWith } from 'rxjs';
import { CustomResponse } from './interface/custom-response';
import { AppState } from './interface/app-state';
import { DataState } from './enum/data-state.enum';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [CommonModule],
  standalone: true,
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  appState$: Observable<AppState<CustomResponse>> | undefined;

  constructor(private serverService: ServerService) {}

  ngOnInit(): void {
    this.appState$ = this.serverService.servers$.pipe(
      map((response) => {
        return { dataState: DataState.LOADED_STATE, data: response };
      }),
      startWith({ dataState: DataState.LOADING_STATE }),
      catchError((error: string) =>
        of({ dataState: DataState.ERROR_STATE, error })
      )
    );
  }
}
