'use client';

import style from './rightSearchZone.module.css';
import { useSelectedLayoutSegment } from 'next/navigation';
import SearchInput from '@/app/(afterLogin)/_component/SearchInput';

const RightSearchZone = () => {
    const segment = useSelectedLayoutSegment();

    if (segment === 'explore') return null;
    else if (segment === 'search')
        return (
            <>
                <section className={style.container}>
                    <h2 className={style.title}>Search filters</h2>
                    <div className={style.radioContainer}>
                        <div className={style.radioWrapper}>
                            <span>People</span>
                            <div>
                                <label htmlFor='anyone'>From anyone</label>
                                <input type='radio' name='people' id='anyone' />
                            </div>
                            <div>
                                <label htmlFor='follow'>People you follow</label>
                                <input type='radio' name='people' id='follow' />
                            </div>
                        </div>
                        <div className={style.radioWrapper}>
                            <span>Location</span>
                            <div>
                                <label htmlFor='anywhere'>Anywhere</label>
                                <input type='radio' name='location' id='anywhere' />
                            </div>
                            <div>
                                <label htmlFor='near'>Near you</label>
                                <input type='radio' name='location' id='near' />
                            </div>
                        </div>
                    </div>
                </section>
                <div className={style.horizontalLine}></div>
            </>
        );
    return (
        <>
            <SearchInput />
        </>
    );
};

export default RightSearchZone;
