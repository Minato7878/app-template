import { CollectionViewer, DataSource } from "@angular/cdk/collections";
import { RailwayDto } from 'src/app/model/railway.model';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { finalize } from 'rxjs/operators';
import { RailwayService } from 'src/app/_service/railway.service';

export class RailwayDataSource implements DataSource<RailwayDto> {

    private railwaySubject = new BehaviorSubject<RailwayDto[]>([]);
    private loadingSubject = new BehaviorSubject<boolean>(false);

    public loading$ = this.loadingSubject.asObservable();

    constructor(private railwayService: RailwayService) { }

    connect(collectionViewer: CollectionViewer): Observable<RailwayDto[]> {
        return this.railwaySubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.railwaySubject.complete();
        this.loadingSubject.complete();
    }

    loadRailways(filter = '', sortDirection = 'asc', pageIndex: number, pageSize: number) {
        this.loadingSubject.next(true);
        this.railwayService.getPageableView(filter, sortDirection, pageIndex, pageSize).pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false))
        )
        .subscribe(railways => this.railwaySubject.next(railways));
    }
}
