--Lua脚本

--- 限流KEY资源唯一标识
local key =  "rate.limit;"..KEYS[1]
print(key)
-- 最大并发数  时间
local limit = tonumber(ARGV[1])
local expire_time = ARGV[2]

-- 检测key 是否存在
local is_exists = redis.call('EXISTS',key)

-- 如果超出限流大小
if is_exists == 1 then
	if redis.call("INCR",key) > limit then
		return 0
	else 
		return 1
	end
else 
	redis.call('SET',key,1)
	redis.call('expire',key,expire_time)
	return 1
	
end