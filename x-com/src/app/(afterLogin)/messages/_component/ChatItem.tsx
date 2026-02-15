'use client';

import style from './chatItem.module.css';
import Image from 'next/image';
import dayjs from 'dayjs';
import 'dayjs/locale/ko';
import relativeTime from 'dayjs/plugin/relativeTime';
import { faker } from '@faker-js/faker/locale/ko';
import { useRouter } from 'next/navigation';

dayjs.extend(relativeTime);
dayjs.locale('ko');

const ChatItem = () => {
    const route = useRouter();

    const user = {
        id: 'Elon_Musk',
        name: 'elonmusk',
        image: faker.image.avatar(),
        Messages: [
            { roomId: 123, content: '안녕하세요.', createdAt: dayjs(new Date()).fromNow(true) },
            { roomId: 123, content: '안녕히가세요.', createdAt: dayjs(new Date()).fromNow(true) },
        ],
    };

    const handleClick = () => {
        route.push(`/messages/${user.Messages.at(-1)?.roomId}`);
    };

    return (
        <li className={style.item}>
            <button type='button' className={style.button} onClick={handleClick}>
                <Image className={style.profile} src={user.image} alt='프로필' width={50} height={50} />
                <div className={style.messageWrapper}>
                    <div className={style.userInfo}>
                        <span className={style.name}>{user.name}</span>
                        <span className={style.id}>
                            @{user.id}&nbsp;•&nbsp;{user.Messages.at(-1)?.createdAt}
                        </span>
                    </div>
                    <p className={style.content}>{user.Messages.at(-1)?.content}</p>
                </div>
            </button>
        </li>
    );
};

export default ChatItem;
