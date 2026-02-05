import style from './page.module.css';
import Trend from '@/app/(afterLogin)/_component/Trend';

const Page = () => {
    const trendsDummyData = [
        { tagId: 1, title: 'SK하이닉스', count: 1234 },
        { tagId: 2, title: 'GOOGL', count: 5643 },
        { tagId: 3, title: '두바이쫀득쿠키', count: 32 },
        { tagId: 4, title: 'GOLD', count: 89 },
    ];

    return (
        <section className={style.container}>
            <div className={style.searchBox}>
                <label htmlFor='search' className='sr-only'>
                    검색
                </label>
                <input type='text' id='search' />
            </div>
            <h2 className={style.title}>나를 위한 트렌드</h2>
            <ul className={style.box}>
                {trendsDummyData.map(trend => (
                    <Trend key={trend.tagId} trend={trend} />
                ))}
            </ul>
        </section>
    );
};

export default Page;
