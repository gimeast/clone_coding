import style from './trend.module.css';
import Link from 'next/link';

interface TrendType {
    tagId: number;
    title: string;
    count: number;
}
const Trend = ({ trend }: { trend: TrendType }) => {
    return (
        <li className={style.trend}>
            <Link href={`/search?q=${encodeURIComponent(trend.title)}`}>
                <span>실시간트렌드</span>
                <strong>{trend.title}</strong>
                <span>{trend.count.toLocaleString()} posts</span>
            </Link>
        </li>
    );
};

export default Trend;
