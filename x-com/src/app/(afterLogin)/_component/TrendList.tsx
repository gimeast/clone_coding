import style from './trendList.module.css';
import Trend from '@/app/(afterLogin)/_component/Trend';
const TrendList = () => {
    const trendsDummyData = [
        { tagId: 1, title: 'SK하이닉스', count: 1234 },
        { tagId: 2, title: 'GOOGL', count: 5643 },
        { tagId: 3, title: '두바이쫀득쿠키', count: 32 },
        { tagId: 4, title: 'GOLD', count: 89 },
    ];
    return (
        <aside className={style.container}>
            <h3 className={style.title}>What’s happening</h3>
            <ul className={style.box}>
                {trendsDummyData.map(trend => (
                    <Trend key={trend.tagId} trend={trend} />
                ))}
            </ul>
        </aside>
    );
};

export default TrendList;
