--Lua脚本

--- 限流KEY资源唯一标识

local key =  "rate.limit;"..KEYS[1]
-- 最大并发数
local limit = tonumber(ARGV[1])
-- 当前最大并发数
local current = tonumber(redis.call('get',key) or "0")
-- 如果超出限流大小
if current+1 > limit then
	return 0
else 
	redis.call('INCRBY',key,"1")
	redis.call('expire',key,"2")
	return current+1
	
end