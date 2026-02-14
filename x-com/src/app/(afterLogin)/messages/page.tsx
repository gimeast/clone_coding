import style from './page.module.css';
import 'dayjs/locale/ko';
import Message from '@/app/(afterLogin)/messages/_component/Message';

const Page = () => {
    return (
        <section className={style.container}>
            <h2 className={style.header}>쪽지</h2>
            <ul>
                <Message />
                <Message />
                <Message />
            </ul>
        </section>
    );
};

export default Page;
